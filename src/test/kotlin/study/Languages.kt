package study

data class Languages(val languages: List<Language>) {
    operator fun contains(language: Language): Boolean = languages.contains(language)
}
