package blackjack.domain.gamer

import blackjack.domain.card.CardDeck
import blackjack.domain.card.cards
import blackjack.domain.shuffle.CardNotShuffler
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class PlayersTest : BehaviorSpec({

    Given("플레이어 목록이 만들어 졌을 때") {
        val players = Players.create(playerInitProperties("test1", "test2"))

        When("카드를 배분하지 않았다면") {
            Then("플레이어들은 카드를 갖고 있지 않다") {
                players.notHasCards() shouldBe true
            }
        }
    }

    Given("플레이어들에게 카드를 배분하지 않았을 때") {
        val players = Players.create(playerInitProperties("test1", "test2"))

        When("대기중 플레이어를 가져오면") {
            Then("RuntimeException 예외 처리를 한다") {
                shouldThrow<RuntimeException> {
                    players.requireWaitPlayer()
                }
            }
        }
    }

    Given("2명의 플레이어가 존재할 때 ") {
        val playerInitProperties = playerInitProperties("test1", "test2")

        When("카드를 배분 했다면") {
            val cardDeck = CardDeck.create(CardNotShuffler())
            val players = Players.create(playerInitProperties)
            players.init { cards(cardDeck.poll(), cardDeck.poll()) }

            Then("대기중인 플레이어는 첫번째 플레이어다") {
                players.requireWaitPlayer().name shouldBe playerInitProperties.first().playerName
            }
        }

        When("첫번째 플레이어가 스테이 했다면") {
            val cardDeck = CardDeck.create(CardNotShuffler())
            val players = Players.create(playerInitProperties)
            players.init { cards(cardDeck.poll(), cardDeck.poll()) }
            players.requireWaitPlayer().stay()

            Then("대기중인 플레이어는 두번째 플레이어다") {
                players.requireWaitPlayer().name shouldBe playerInitProperties[1].playerName
            }
        }
    }

    Given("플레이어에게 카드를 배분 했을 때") {
        val playerInitProperties = playerInitProperties("test1", "test2")
        val cardDeck = CardDeck.create(CardNotShuffler())
        val cards1 = cards(cardDeck.poll(), cardDeck.poll())
        val cards2 = cards(cardDeck.poll(), cardDeck.poll())
        val cards = mutableListOf(cards1, cards2)
        val players = Players.create(playerInitProperties)
        players.init { cards.removeFirst() }

        When("캡쳐를 하면") {
            val captures = players.captureAllPlayerCards()

            Then("플레이어들의 카드 목록을 반환한다") {
                captures[0] shouldBe PlayerCards(playerInitProperties[0].playerName, cards1)
                captures[1] shouldBe PlayerCards(playerInitProperties[1].playerName, cards2)
            }
        }
    }
})
