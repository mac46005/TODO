package com.preciado.todo.core.helpers

import java.util.Locale

object CapitalizeWords{
    private fun separateWords(sentence: String): List<String>{
        return sentence.split(' ')
    }
    private fun capitalizeWords(semtence: String): List<String>{
        var wordList = mutableListOf<String>()
        for (word in separateWords(semtence)){
            wordList.add(word.replaceFirstChar { if(it.isLowerCase()) it.titlecase( Locale.ROOT) else it.toString() })
        }
        return wordList
    }
    fun getCaptilizedSentence(sentence: String): String {
        var capitalizedWordList = capitalizeWords(sentence)
        return capitalizedWordList.joinToString(" ")
    }
}