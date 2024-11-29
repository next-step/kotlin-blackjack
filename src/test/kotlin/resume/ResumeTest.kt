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

    @Test
    fun `soft skill`() {
        val person =
            introduce {
                name("Elon Musk")
                company("DOGE")
                skills {
                    soft("A passion for problem solving")
                }
            }
        person.skills.size shouldBe 1
        person.skills[0] shouldBe SoftSkill("A passion for problem solving")
    }

    @Test
    fun `hard skill`() {
        val person =
            introduce {
                name("Elon Musk")
                company("DOGE")
                skills {
                    hard("Shooting rockets into space")
                }
            }
        person.skills.size shouldBe 1
        person.skills[0] shouldBe HardSkill("Shooting rockets into space")
    }

    @Test
    fun `mix of skills`() {
        val person =
            introduce {
                name("Elon Musk")
                company("DOGE")
                skills {
                    soft("A passion for problem solving")
                    soft("Posting dank memes")
                    hard("Shooting rockets into space")
                }
            }
        person.skills.size shouldBe 3
        person.skills[0] shouldBe SoftSkill("A passion for problem solving")
        person.skills[1] shouldBe SoftSkill("Posting dank memes")
        person.skills[2] shouldBe HardSkill("Shooting rockets into space")
    }
}
