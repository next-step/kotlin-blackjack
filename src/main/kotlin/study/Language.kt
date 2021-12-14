package study

sealed class Language

data class Korean(val level: Int) : Language()

data class English(val level: Int) : Language()
