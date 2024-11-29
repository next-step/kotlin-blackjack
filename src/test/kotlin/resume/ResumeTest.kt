package resume

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/*
introduce {
  name("Elon Musk")
  company("DOGE")
  skills {
    soft("A passion for problem solving")
    soft("Posting dank memes")
    hard("Shooting rockets into space")
  }
  languages {
    "English" level 5
    "Afrikaans" level 3
  }
}
*/
class ResumeTest {
    @ParameterizedTest
    @ValueSource(strings = ["Elon Musk", "홍길동"])
    fun name(value: String) {
        val person =
            introduce {
                name(value)
            }
        person.name shouldBe value
    }

    @Test
    fun company() {
        val person =
            introduce {
                name("Elon Musk")
                company("DOGE")
            }
        person.company shouldBe "DOGE"
    }
}
