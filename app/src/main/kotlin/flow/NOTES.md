### Kotlin Flows
- Streams - used to get continuous data/changes from observable(DB/Repository) to an observer (Client).
- Pipeline - producer puts data at one end and consumed at other.
- Cold streams are preferred over hot streams -> hot streams may waste resources, manual close
- But cold stream closes when no client available.
- Problems -> producer is too fast for consumer to handle, to solve it we use buffering, but 
capacity of buffer is also a problem i.e. how much capacity to use.
- So instead of thread blocking we use coroutines to suspend the consumer or producer as the need
may be to handle run away condition.
- Flows API helps us implement streams with async properties.
- Flows has 2 type of operators - terminal and non-terminal.
- terminal - start flow e.g collect , these terminal operators are all suspend.
- non-terminal are all non-suspending function and also return a flow.
---
| ShareFlow                                                            | StateFlow                                | LiveData                               |
|:---------------------------------------------------------------------|:-----------------------------------------|:---------------------------------------|
| HotFlow but doesn't have a state.Can be executed on different thread | HotFlow but have a state with last data. | All operators are done on MainThread() |
| More operators                                                       | More operators                           | Less Operators                         |
| Lifecycle independent                                                | Lifecycle independent                    | Lifecycle dependent                    |