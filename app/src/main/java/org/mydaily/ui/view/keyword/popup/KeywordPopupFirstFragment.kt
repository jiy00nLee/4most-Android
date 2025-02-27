package org.mydaily.ui.view.keyword.popup

import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.FragmentKeywordPopupFirstBinding
import org.mydaily.ui.base.BaseFragment
import org.mydaily.ui.viewmodel.GoalViewModel
import org.mydaily.ui.viewmodel.KeywordViewModel

class KeywordPopupFirstFragment : BaseFragment<FragmentKeywordPopupFirstBinding, KeywordViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_keyword_popup_first
    override val viewModel: KeywordViewModel by viewModel()

    override fun initView() {

    }

    override fun initBeforeBinding() {
    }

    override fun initAfterBinding() {
    }


}