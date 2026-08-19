# Benji UI Test Plan

The `test-ui` skill compiles the Java source and runs each test in order. It compares
the full program output with the expected output. It stops at the first failure.

## Level 4: add and list all task types

Aim: Verify that Todo, Deadline, and Event tasks are stored as `Task` objects and displayed with the correct type-specific details.

### Input

```text
todo borrow book
deadline return book /by Sunday
event project meeting /from Mon 2pm /to 4pm
list
bye
```

### Expected output

```text
____________________________________________________________
  BBBBB   EEEEEEE  NN   NN  JJJJJJJ  IIIIIII
  BB  BB  EE       NNN  NN     JJJ     III
  BBBBB   EEEEE    NN N NN     JJJ     III
  BB  BB  EE       NN  NNN  JJ JJJ     III
  BBBBB   EEEEEEE  NN   NN   JJJJJ   IIIIIII

Hello! I'm BENJI.
What can I do for you?
____________________________________________________________
Got it. I've added this task:
  [T][ ] borrow book
Now you have 1 tasks in the list.
____________________________________________________________
Got it. I've added this task:
  [D][ ] return book (by: Sunday)
Now you have 2 tasks in the list.
____________________________________________________________
Got it. I've added this task:
  [E][ ] project meeting (from: Mon 2pm to: 4pm)
Now you have 3 tasks in the list.
____________________________________________________________
Here are the tasks in your list:
1.[T][ ] borrow book
2.[D][ ] return book (by: Sunday)
3.[E][ ] project meeting (from: Mon 2pm to: 4pm)
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```
