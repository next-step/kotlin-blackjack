package dsl.performer

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContain

// Found interface kotlin.time.TimeMark, but class was expected

class LanguageTest : StringSpec({

    "languages 를 DSL 로 생성 해야 한다" {
        val actual = languages {
            "Korean" level 4
            "English" level 5
        }
        actual shouldContain Language("Korean", 4)
        actual shouldContain Language("English", 5)
    }
})

fun languages(block: LanguageBuilder.() -> Unit): List<Language> {
    val languageBuilder = LanguageBuilder().apply(block)
    languageBuilder.languages.addAll(languageBuilder.languages)
    return languageBuilder.languages
}
