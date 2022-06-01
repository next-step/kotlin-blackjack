package camp.nextstep.study

data class Languages(val languages: Map<Language, Language.Level>) {

    fun size() = languages.size

    operator fun get(language: String) = languages[Language(language)]

}