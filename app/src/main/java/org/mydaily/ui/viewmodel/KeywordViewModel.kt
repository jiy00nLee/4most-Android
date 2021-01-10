package org.mydaily.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.mydaily.ui.base.BaseViewModel

class KeywordViewModel : BaseViewModel() {

    private val _lifeWordList = MutableLiveData<List<String>>()
    val lifeWordList: LiveData<List<String>>
        get() = _lifeWordList

    private val _workWordList = MutableLiveData<List<String>>()
    val workWordList: LiveData<List<String>>
        get() = _workWordList


    private val _myWordList = MutableLiveData<List<String>>()
    val myWordList: LiveData<List<String>>
        get() = _myWordList


    private val _addMyWordList = mutableListOf<String>()
    val addMyWordList : List<String>
        get() = _addMyWordList

    //삶을 대하는 자세
    fun getLifeWord() {
        /* 임시 데이터 */
        val tempList = listOf(
            "진정성", "용기", "열정", "꾸준함", "배움", "선한영향력", "아웃풋", "행복", "즐거움", "현명", "타당성", "정당성"
        )
        _lifeWordList.value = tempList
    }

    //일을 대하는 자세
    fun getWorkWord() {
        /* 임시 데이터 */
        val tempList = listOf(
            "진정성", "용기", "열정", "꾸준함", "배움", "선한영향력", "아웃풋", "행복", "즐거움", "현명", "타당성", "정당성"
        )
        _workWordList.value = tempList
    }

    //내가 추가한 단어
    fun getMyWord() {

    }

    fun addMyWord(keyword : String) {
        _addMyWordList.add(keyword)
    }
}