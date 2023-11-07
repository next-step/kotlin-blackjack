package study

data class Language private constructor(val language: Pair<String, Int>) {
    companion object {
        fun from(language: String, level: Int) = Language(language to level)
    }
}