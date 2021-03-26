package study.domain

class Languages(val languages: List<Language>) {
    fun toList(): List<Language> {
        return this.languages
    }
}
