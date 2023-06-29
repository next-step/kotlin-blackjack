package blackjack.controller

import blackjack.model.BlackjackDealer
import blackjack.model.BlackjackParticipant
import blackjack.model.BlackjackParticipants
import blackjack.model.BlackjackPlayer
import blackjack.model.BlackjackRevenueJudge
import blackjack.model.CardDeck
import blackjack.model.PlayerName

data class BlackjackGame(
    private val moreWantedCardPredicate: (String) -> Boolean,
    private val bettingMoneyProvider: (String) -> Int,
    private val playerNamesProvider: () -> Collection<String>,
    private val blackjackDealerMoreCardScoreLimitConsumer: (Int) -> Unit,
    private val blackjackPlayerConsumer: (BlackjackPlayer) -> Unit,
    private val blackjackPlayersCardCountConsumer: (BlackjackDealer, Collection<BlackjackPlayer>, Int) -> Unit,
    private val blackjackPlayersScoreConsumer: (BlackjackDealer, Collection<BlackjackPlayer>) -> Unit,
    private val blackjackJudgeConsumer: (BlackjackRevenueJudge) -> Unit,
) {
    fun start() {
        val deck = CardDeck()
        val dealer = BlackjackDealer(deck, blackjackDealerMoreCardScoreLimitConsumer)
        val players = playerNamesProvider().map {
            BlackjackPlayer(
                deck,
                bettingMoneyProvider,
                PlayerName(it),
                blackjackPlayerConsumer,
                moreWantedCardPredicate
            )
        }

        BlackjackParticipants(players + dealer).also {
            blackjackPlayersCardCountConsumer(dealer, players, BlackjackParticipant.INITIAL_DEALING_COUNT)
        }.apply {
            draw(deck)
        }
        blackjackPlayersScoreConsumer(dealer, players)
        blackjackJudgeConsumer(BlackjackRevenueJudge(dealer, players))
    }
}
