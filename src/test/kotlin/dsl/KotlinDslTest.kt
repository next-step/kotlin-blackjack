import dsl.Skills.SkillType
import dsl.introduce
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class KotlinDslTest {
    @Test
    fun introduce() {
        val person = introduce {
            name("김좌훈")
            company("토스")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
            languages {
                "Korean" level 5
                "English" level 3
            }
        }

        person.name shouldBe "김좌훈"
        person.company shouldBe "토스"
        person.skills.skills shouldHaveSize 3
        person.skills.skills[0].name shouldBe "A passion for problem solving"
        person.skills.skills[0].type shouldBe SkillType.SOFT
        person.skills.skills[1].name shouldBe "Good communication skills"
        person.skills.skills[1].type shouldBe SkillType.SOFT
        person.skills.skills[2].name shouldBe "Kotlin"
        person.skills.skills[2].type shouldBe SkillType.HARD
        person.languages.languages shouldBe mapOf("Korean" to 5, "English" to 3)
    }
}
