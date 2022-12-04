typealias Language = Pair<String, Int>

data class Languages(
    val languages: List<Language>,
) {
    class Builder {
        private val languages = mutableListOf<Language>()

        infix fun String.level(level: Int) {
            require(level in 1..5) { "Level은 1부터 5까지 가능합니다." }
            languages.add(this to level)
        }

        fun build(): Languages {
            return Languages(languages)
        }
    }
}
