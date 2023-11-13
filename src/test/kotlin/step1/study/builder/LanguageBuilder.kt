package step1.study.builder

/**
 * 언어 객체 빌더
 * */
class LanguageBuilder {

    private val languages: MutableMap<String, Int> = mutableMapOf()
    infix fun String.level(level: Int) {
        languages[this] = level
    }

    fun build() = languages
}