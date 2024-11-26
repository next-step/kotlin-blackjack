package study.domain

data class Language(val name: String, val level: Int) {
    companion object {
        infix fun String.level(level: Int): Language {
            return Language(this, level)
        }
    }
}
