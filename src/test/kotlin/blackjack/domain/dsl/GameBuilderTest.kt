package blackjack.domain.dsl

import blackjack.domain.FaceType
import blackjack.domain.SymbolType
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldHaveSize

class GameBuilderTest : FunSpec({
    test("DSL로 게임을 구성할 수 있다.") {
        val game = buildGame {
            deck {
                aceCards(SymbolType.DIAMOND, SymbolType.CLOVER)
                faceCards {
                    SymbolType.DIAMOND to FaceType.KING and FaceType.QUEEN
                }
                numberCards {
                    2 until 3 from SymbolType.CLOVER
                }
            }

            players {
                name("catsbi")
                bet(1000.0)

                name("pobi")
                bet(1000.0)
            }
        }

        game.players shouldHaveSize 2
    }
})
