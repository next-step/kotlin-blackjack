package next.step.dsl

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class LanguagesTest {
    @Test
    fun languages() {
        val languages: Languages = languages {
            "Korean" level 5
            "English" level 3
        }
        
        languages.get("Korean") shouldBe 5
        languages.get("English") shouldBe 3
    }

}