package study

import dsl.SkillType
import dsl.introduce
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.inspectors.forAll
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe

data class PersonDslTestData(
    val name: String,
    val company: String? = null,
    val softSkills: List<String> = emptyList(),
    val hardSkills: List<String> = emptyList(),
    val languages: List<Pair<String, Int>> = emptyList(),
)

class DslTestKotest : FunSpec({
    context("사람의 요소를 모두 추가") {
        withData(
            listOf(
                PersonDslTestData(
                    "홍길동",
                    "활빈당",
                    listOf("착한 마음씨", "호형호제 못함"),
                    listOf("전투", "곳간 털기"),
                    listOf(Pair("Korean", 5), Pair("English", 0))
                )
            )
        ) { it ->
            val person = introduce {
                name(it.name)
                if (it.company != null)
                    company(it.company)
                skills {
                    it.softSkills.forEach {
                        soft(it)
                    }
                    it.hardSkills.forEach {
                        hard(it)
                    }
                }
                languages {
                    it.languages.forEach {
                        it.first level it.second
                    }
                }
            }

            person.name shouldBe it.name
            person.company shouldBe it.company
            person.skills.filter { skill -> skill.type == SkillType.SOFT }.forAll { softSkill ->
                it.softSkills shouldContain softSkill.name
            }
            person.skills.filter { skill -> skill.type == SkillType.HARD }.forAll { hardSkill ->
                it.hardSkills shouldContain hardSkill.name
            }
            person.languages.forAll { language ->
                it.languages shouldContain language.toPair()
            }
        }
    }

    context("사람에게는 이름이 필수") {
        val exception = shouldThrow<IllegalArgumentException> {
            introduce {
            }
        }

        exception.message shouldBe "Please initialize person's name."
    }
})
