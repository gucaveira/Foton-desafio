package com.gustavo.foton.desafio.marvel.presentation.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.gustavo.foton.desafio.marvel.databinding.FragmentCharactersBinding
import com.gustavo.foton.desafio.marvel.framework.imageLoader.ImageLoader
import com.gustavo.foton.desafio.marvel.presentation.characters.adapter.CharactersAdapter
import com.gustavo.foton.desafio.marvel.presentation.characters.adapter.CharactersLoadMoreStateAdapter
import com.gustavo.foton.desafio.marvel.presentation.characters.adapter.CharactersRefreshStateAdapter
import com.gustavo.foton.desafio.marvel.presentation.detail.DetailViewArg
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharactersFragment : Fragment() {

    private var _binding: FragmentCharactersBinding? = null
    private val binding: FragmentCharactersBinding get() = _binding!!

    private val viewModel: CharactersViewModel by viewModels()

    @Inject
    lateinit var imageLoader: ImageLoader

    private val headerAdapter: CharactersRefreshStateAdapter by lazy {
        CharactersRefreshStateAdapter(
            characterAdapter::retry
        )
    }

    private val characterAdapter: CharactersAdapter by lazy {
        CharactersAdapter(imageLoader) { character, view ->
            val extras = FragmentNavigatorExtras(view to character.name)


            val directions = CharactersFragmentDirections.actionCharactersFragmentToDetailFragment(
                character.name, DetailViewArg(character.id, character.name, character.imageUrl)
            )

            findNavController().navigate(directions, extras)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentCharactersBinding.inflate(inflater, container, false).apply {
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCharactersAdapter()
        observeInitialLoadState()

        viewModel.state.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is CharactersViewModel.UiState.SearchResult -> {
                    lifecycleScope.launch {
                        characterAdapter.submitData(uiState.data)
                    }
                }
            }
        }
        viewModel.searchCharacters()
    }

    private fun initCharactersAdapter() {
        binding.recyclerCharacters.run {
            postponeEnterTransition()

            setHasFixedSize(true)
            adapter = characterAdapter.withLoadStateHeaderAndFooter(
                header = headerAdapter,
                footer = CharactersLoadMoreStateAdapter(characterAdapter::retry)
            )

            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
        }
    }

    private fun observeInitialLoadState() {
        lifecycleScope.launch {
            characterAdapter.loadStateFlow.collect { loadState ->

                headerAdapter.loadState = loadState.mediator?.refresh?.takeIf {
                    it is LoadState.Error && characterAdapter.itemCount > 0
                } ?: loadState.prepend


                binding.flipperCharacters.displayedChild = when {
                    loadState.mediator?.refresh is LoadState.Loading -> {
                        setShimmerVisibility(true)
                        FLIPPER_CHILD_LOADING
                    }

                    loadState.mediator?.refresh is LoadState.Error
                            && characterAdapter.itemCount == 0 -> {
                        setShimmerVisibility(false)
                        binding.includeViewCharactersErrorState.buttonRetry.setOnClickListener {
                            characterAdapter.retry()
                        }
                        FLIPPER_CHILD_ERROR
                    }

                    loadState.source.refresh is LoadState.NotLoading ||
                            loadState.mediator?.refresh is LoadState.NotLoading -> {
                        setShimmerVisibility(false)
                        FLIPPER_CHILD_CHARACTERS
                    }
                    else -> {
                        setShimmerVisibility(false)
                        FLIPPER_CHILD_CHARACTERS
                    }
                }
            }
        }
    }

    private fun setShimmerVisibility(visibility: Boolean) {
        binding.includeViewCharactersLoadingState.shimmerCharacters.run {
            isVisible = visibility
            if (visibility) {
                startShimmer()
            } else stopShimmer()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val FLIPPER_CHILD_LOADING = 0
        private const val FLIPPER_CHILD_CHARACTERS = 1
        private const val FLIPPER_CHILD_ERROR = 2
    }
}