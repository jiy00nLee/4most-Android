package org.mydaily.ui.view.keyword.settings

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.data.model.domain.KeywordPriority
import org.mydaily.databinding.FragmentKeywordPriorityBinding
import org.mydaily.ui.adapter.ItemTouchHelperCallback
import org.mydaily.ui.adapter.KeywordPriorityAdapter
import org.mydaily.ui.base.BaseFragment
import org.mydaily.ui.viewmodel.KeywordViewModel

class KeywordPriorityFragment : BaseFragment<FragmentKeywordPriorityBinding, KeywordViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_keyword_priority
    override val viewModel: KeywordViewModel by viewModel()

    private val keywordPriorityAdapter = KeywordPriorityAdapter()

    override fun initView() {

        initRecyclerView()
    }

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

    private fun initRecyclerView() {
        val itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(keywordPriorityAdapter))

        val dividerItemDecoration = DividerItemDecoration(requireContext(),LinearLayoutManager.VERTICAL)
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.divider_recyclerview)!!)

        binding.rvPriority.apply {
            adapter = keywordPriorityAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(dividerItemDecoration)
            setHasFixedSize(true)
            itemTouchHelper.attachToRecyclerView(this)
        }

        /*임시 데이터*/
        keywordPriorityAdapter.data = listOf(
            KeywordPriority("열정", 1),
            KeywordPriority("아웃풋", 2),
            KeywordPriority("영향력", 3),
            KeywordPriority("경청", 4),
        )
    }


}