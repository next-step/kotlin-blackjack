package study.domain

class Languages(private val languages: List<Language>) {
    fun toList(): List<Language> {
        return this.languages
    }
}
