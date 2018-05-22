/*
@startuml
    left to right direction
    skinparam packageStyle rectangle
    actor player1
    actor player2
    rectangle "tic-tac-toe game"{
        player1 --> (new game)
        player1 --> (check who won)
        player2 --> (new game)
        player1 --> (player1 moves)        
        player2 --> (player2 moves)
        player2 --> (check who won)
        (check who won)<.(Add score to winner):include
        (new game)<.(set board size):extends
    }
@enduml
*/