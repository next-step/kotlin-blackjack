package blackjack.domain.result

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardSymbol
import blackjack.domain.cards
import blackjack.domain.gamePlayers
import blackjack.domain.player.Dealer
import blackjack.domain.player.GamePlayer
import blackjack.domain.player.Players
import blackjack.domain.result.match.MatchState
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder

class GameResultManagerTest : StringSpec({
    "게임 결과를 얻는다" {
        forAll(
            row(
                cards(
                    Card(CardNumber.SEVEN, CardSymbol.SPADE),
                    Card(CardNumber.TEN, CardSymbol.SPADE),
                ),
                MatchState.LOSE
            ),
            row(
                cards(
                    Card(CardNumber.EIGHT, CardSymbol.SPADE),
                    Card(CardNumber.TEN, CardSymbol.SPADE),
                ),
                MatchState.PUSH
            ),
            row(
                cards(
                    Card(CardNumber.NINE, CardSymbol.SPADE),
                    Card(CardNumber.TEN, CardSymbol.SPADE),
                ),
                MatchState.WIN
            ),
        ) { cards, expectedMatch ->
            val player1 = GamePlayer("test", cards)
            val gamePlayers = gamePlayers(player1)

            val dealer = Dealer(
                cards(
                    Card(CardNumber.EIGHT, CardSymbol.HEART),
                    Card(CardNumber.TEN, CardSymbol.HEART),
                )
            )

            val results = GameResultManager.getGameResults(Players(dealer, gamePlayers))

            results shouldContainExactlyInAnyOrder listOf(GameResult(player1, expectedMatch))
        }
    }

    "딜러가 bust 시 남아 있는 플레이는 모두 승이다" {
        table(
            headers("플레이어1 카드", "플레이어1 승패 결과", "플레이어2 카드", "플레이어2 승패 결과"),
            row(
                cards(
                    Card(CardNumber.JACK, CardSymbol.HEART),
                    Card(CardNumber.QUEEN, CardSymbol.HEART),
                    Card(CardNumber.TEN, CardSymbol.SPADE),
                ),
                MatchState.LOSE,
                cards(
                    Card(CardNumber.NINE, CardSymbol.HEART),
                    Card(CardNumber.TEN, CardSymbol.HEART),
                ),
                MatchState.WIN,
            ),
            row(
                cards(
                    Card(CardNumber.JACK, CardSymbol.HEART),
                    Card(CardNumber.QUEEN, CardSymbol.HEART),
                    Card(CardNumber.TEN, CardSymbol.SPADE),
                ),
                MatchState.LOSE,
                cards(
                    Card(CardNumber.NINE, CardSymbol.HEART),
                    Card(CardNumber.TEN, CardSymbol.HEART),
                ),
                MatchState.WIN,
            )
        ).forAll { cards1, expect1, cards2, expect2 ->
            val player1 = GamePlayer("test", cards1)
            val player2 = GamePlayer("test", cards2)

            val gamePlayers = gamePlayers(player1, player2)

            val dealer = Dealer(
                cards(
                    Card(CardNumber.TEN, CardSymbol.CLUB),
                    Card(CardNumber.TEN, CardSymbol.HEART),
                    Card(CardNumber.TEN, CardSymbol.DIAMOND),
                )
            )

            val results = GameResultManager.getGameResults(Players(dealer, gamePlayers))

            results shouldContainExactlyInAnyOrder listOf(
                GameResult(player1, expect1),
                GameResult(player2, expect2),
            )
        }
    }

    "딜러가 normal 상태일 때 승부" {
        table(
            headers("플레이어1 카드", "플레이어1 승패 결과", "플레이어2 카드", "플레이어2 승패 결과"),
            row(
                cards(
                    Card(CardNumber.SEVEN, CardSymbol.HEART),
                    Card(CardNumber.TEN, CardSymbol.SPADE),
                ),
                MatchState.LOSE,
                cards(
                    Card(CardNumber.NINE, CardSymbol.HEART),
                    Card(CardNumber.TEN, CardSymbol.HEART),
                ),
                MatchState.WIN,
            ),
            row(
                cards(
                    Card(CardNumber.EIGHT, CardSymbol.HEART),
                    Card(CardNumber.TEN, CardSymbol.SPADE),
                ),
                MatchState.PUSH,
                cards(
                    Card(CardNumber.NINE, CardSymbol.HEART),
                    Card(CardNumber.TEN, CardSymbol.HEART),
                ),
                MatchState.WIN,
            )
        ).forAll { cards1, expect1, cards2, expect2 ->
            val player1 = GamePlayer("test", cards1)
            val player2 = GamePlayer("test", cards2)

            val gamePlayers = gamePlayers(player1, player2)

            val dealer = Dealer(
                cards(
                    Card(CardNumber.EIGHT, CardSymbol.CLUB),
                    Card(CardNumber.TEN, CardSymbol.HEART),
                )
            )

            val results = GameResultManager.getGameResults(Players(dealer, gamePlayers))

            results shouldContainExactlyInAnyOrder listOf(
                GameResult(player1, expect1),
                GameResult(player2, expect2),
            )
        }
    }

    "딜러가 BlackJack 상태일 때 승부" {
        table(
            headers("플레이어1 카드", "플레이어1 승패 결과", "플레이어2 카드", "플레이어2 승패 결과"),
            row(
                cards(
                    Card(CardNumber.ACE, CardSymbol.HEART),
                    Card(CardNumber.QUEEN, CardSymbol.HEART),
                ),
                MatchState.PUSH,
                cards(
                    Card(CardNumber.NINE, CardSymbol.HEART),
                    Card(CardNumber.TEN, CardSymbol.HEART),
                ),
                MatchState.LOSE,
            ),
            row(
                cards(
                    Card(CardNumber.JACK, CardSymbol.HEART),
                    Card(CardNumber.QUEEN, CardSymbol.HEART),
                    Card(CardNumber.TEN, CardSymbol.SPADE),
                ),
                MatchState.LOSE,
                cards(
                    Card(CardNumber.NINE, CardSymbol.HEART),
                    Card(CardNumber.TEN, CardSymbol.HEART),
                ),
                MatchState.LOSE,
            )
        ).forAll { cards1, expect1, cards2, expect2 ->
            val player1 = GamePlayer("test", cards1)
            val player2 = GamePlayer("test", cards2)

            val gamePlayers = gamePlayers(player1, player2)

            val dealer = Dealer(
                cards(
                    Card(CardNumber.ACE, CardSymbol.CLUB),
                    Card(CardNumber.JACK, CardSymbol.HEART),
                )
            )

            val results = GameResultManager.getGameResults(Players(dealer, gamePlayers))

            results shouldContainExactlyInAnyOrder listOf(
                GameResult(player1, expect1),
                GameResult(player2, expect2),
            )
        }
    }
})
