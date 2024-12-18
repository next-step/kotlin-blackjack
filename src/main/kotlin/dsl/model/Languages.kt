package dsl.model

class Languages(languages: List<Language>) {
    val languages = languages.toList()
        get() = field.toList()
}
