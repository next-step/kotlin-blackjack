package study

data class Language(val type: String, val level: Int) {

    companion object {

        class LanguageBuilder {
            private var languages = mutableListOf<Language>()

            infix fun String.level(level: Int) {
                languages.add(Language(this, level))
            }

            fun build(): List<Language> = languages
        }
    }
}
