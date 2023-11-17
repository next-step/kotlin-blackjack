package study

import dsl.SkillType
import dsl.introduce
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe

class DslTest : FunSpec({
    test("사람의 요소를 모두 추가") {
        val person = introduce {
            name("홍길동")
            company("활빈당")
            skills {
                soft("착한 마음씨")
                soft("정의로움")
                hard("싸움")
            }
            languages {
                "Korean" level 5
                "English" level 0
            }
        }

        person.name shouldBe "홍길동"
        person.company shouldBe "활빈당"
        listOf("착한 마음씨", "정의로움").forAll {
            person.skills.filter { it.type == SkillType.SOFT }.map { it.name } shouldContain it
        }
        listOf("싸움").forAll {
            person.skills.filter { it.type == SkillType.HARD }.map { it.name } shouldContain it
        }

        listOf(Pair("Korean", 5), Pair("English", 0)).forAll {
            person.languages.map { mapEntry -> Pair(mapEntry.key, mapEntry.value) } shouldContain it
        }
    }

    test("사람에게는 이름이 필수") {
        val exception = shouldThrow<IllegalArgumentException> {
            introduce {
            }
        }

        exception.message shouldBe "Please initialize person's name."
    }
})
