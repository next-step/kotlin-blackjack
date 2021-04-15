package study

class Language(language: String, level: Int) {
    val pair: Pair<String, Int> = Pair(language, level)

    override fun equals(other: Any?): Boolean {
        if (other is Language) {
            return pair.first == other.pair.first && pair.second == other.pair.second
        }
        return super.equals(other)
    }
}
