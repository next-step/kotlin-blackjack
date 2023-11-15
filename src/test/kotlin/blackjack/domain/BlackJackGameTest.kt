package blackjack.domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.style.DescribeSpec

class BlackJackGameTest : DescribeSpec({
    describe("게임 생성") {
        context("게임에 참여할 2명의 이름 전달") {
            it("게임 생성") {
                shouldNotThrowAny {
                    BlackJackGame(PlayerNames(listOf(PlayerName("Hong"), PlayerName("Kim"))))
                }
            }
        }
    }
})
