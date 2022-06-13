package blackjack.application

import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table

class BlackJackTests : DescribeSpec({

    describe("블랙잭은") {
        it("2명에서 6명 사이의 참가자가 있어야 진행 가능하다") {
            io.kotest.data.forAll(
                table(
                    headers("딜러", "참가자"),
                    row(
                        Dealer(), listOf(Player("1"), Player("2")),
                    ),
                    row(
                        Dealer(), List(6) { Player(it.toString()) }
                    ),
                )
            ) { dealer, players ->
                shouldNotThrowAny {
                    BlackJack(dealer, players)
                }
            }
        }

        it("참가자가 2명에서 6명 사이가 아니라면 진행 가능할 수 없다") {
            io.kotest.data.forAll(
                table(
                    headers("딜러", "참가자"),
                    row(
                        Dealer(), listOf(Player("1")),
                    ),
                    row(
                        Dealer(), List(7) { Player(it.toString()) }
                    ),
                )
            ) { dealer, players ->
                shouldThrowExactly<IllegalArgumentException> {
                    BlackJack(dealer, players)
                }
            }
        }
    }
})
