import dsl.Language
import dsl.Person
import dsl.Skill
import dsl.introduce
import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class ResumeKoTest : FreeSpec({
    val person: Person = introduce {
        name("윤도현")
        company("카카오")
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

    val expectedName = "윤도현"
    val expectedCompany = "카카오"
    val expectedSkillSet = listOf(
        Skill.Soft("A passion for problem solving"),
        Skill.Soft("Good communication skills"),
        Skill.Hard("Kotlin"),
    )

    val expectedLanguageSet = listOf(
        Language("Korean", 5),
        Language("English", 3),
    )

    "이름은 $expectedName 이다." - {
        person.name shouldBe expectedName
    }

    "회사는 $expectedCompany 이다." - {
        person.company shouldBe expectedCompany
    }

    "갖고있는 스킬은 $expectedSkillSet 이다." - {
        person.skills shouldBe expectedSkillSet
    }

    "첫번째 스킬은 ${expectedSkillSet[0]} 이다." - {
        person.skills?.get(0) shouldBe expectedSkillSet[0]
    }

    "할 수있는 언어는 $expectedLanguageSet 이다." - {
        person.languages shouldBe expectedLanguageSet
    }

    "기대값과 모두 일치한다." - {
        assertSoftly(person) {
            name shouldBe expectedName
            company shouldBe expectedCompany
            skills shouldBe expectedSkillSet
            languages shouldBe expectedLanguageSet
        }
    }
})
