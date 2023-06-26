import introduce.Person
import introduce.introduce
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class IntroduceDslTest {

    @ValueSource(strings = ["장원영", "카리나", "지수", "리사"])
    @ParameterizedTest
    fun name(name: String) {
        val person: Person = introduce {
            name(name)
        }
        person.name shouldBe name
        person.company shouldBe ""
    }
    @Test
    fun test_introduce() {
        val person = introduce {
            name("서동성")
            company("트레드링스")
            skills {
                soft("soft 1")
                hard("hard 1")
            }
            languages {
                "Korean" level 5
                "English" level 3
            }
        }
        person.name shouldBe "서동성"
        person.company shouldBe "트레드링스"
        person.skills[0].name shouldBe "soft 1"
        person.skills[1].name shouldBe "hard 1"
        person.languages[0].name shouldBe "Korean"
        person.languages[0].level shouldBe 5
    }
}
