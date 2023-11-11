package study

import dsl.SkillType
import dsl.introduce
import io.kotest.inspectors.forAll
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

/**
 * introduce {
 * name("김영태")
 * company("홍익대학교")
 * skills {
 * soft("A passion for problem solving")
 * soft("Good communication skills")
 * hard("Kotlin")
 * }
 * languages {
 * "Korean" level 5용
 * "English" level 3
 * }
 * }
 */
class DslTest {
    @ParameterizedTest
    @MethodSource("generatePersonArguments")
    fun test(
        name: String,
        company: String,
        softSkills: List<String>,
        hardSkills: List<String>
    ) {
        val person = introduce {
            name(name)
            company(company)
            skills {
                softSkills.forEach {
                    soft(it)
                }
                hardSkills.forEach {
                    hard(it)
                }
            }
        }

        person.name shouldBe name
        person.company shouldBe company
        person.skills.filter { it.type == SkillType.SOFT }.forAll {
            softSkills shouldContain it.name
        }
        person.skills.filter { it.type == SkillType.HARD }.forAll {
            hardSkills shouldContain it.name
        }
    }

    companion object {
        @JvmStatic
        fun generatePersonArguments(): List<Arguments> {
            return listOf(
                Arguments.of(
                    "홍길동",
                    "활빈당",
                    listOf("착한 마음씨", "호형호제 못함"),
                    listOf("전투", "곳간 털기"),
                ),
                Arguments.of(
                    "김영태",
                    "홍익대학교",
                    listOf("웃으면서 거절하기", "거절하면서 웃기"),
                    listOf("Flutter", "Dart"),
                ),
            )
        }
    }
}
