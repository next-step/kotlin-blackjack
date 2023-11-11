package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.types.shouldBeInstanceOf

class NicknameTest : ExpectSpec({

    expect("닉네임이 1글자 미만이면 예외가 발생한다.") {
        shouldThrow<IllegalArgumentException> {
            Nickname("a".repeat(0))
        }
    }

    expect("닉네임이 10글자 초과이면 예외가 발생한다.") {
        shouldThrow<IllegalArgumentException> {
            Nickname("a".repeat(11))
        }
    }

    expect("닉네임이 1 ~ 10 글자이면 객체를 생성한다.") {
        (1..10).toList().forEach {
            Nickname("a".repeat(it)).shouldBeInstanceOf<Nickname>()
        }
    }
})
