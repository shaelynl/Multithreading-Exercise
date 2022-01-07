# Multithreading-Exercise
## Project Title
Multithreading Exercise
## Project Description
Four producer threads A, B, C, D are producing data every one second in the format Data A. Refer to below for details. 
One actor thread reads user input, taking values from 0 to 5. Refer to Actor Thread for more details. 
One consumer thread reads from all five threads and displays the correct producer thread data according to the user input from actor thread. 
Extended the idea of multithreading to TCP communication. Refer to files that end with TCP.
# Appendix
## Data A
Data A comprises of an id which increments every second, a srcid which corresponds to the producer thread (A is 1, B is 2 etc.), and a price which is a random number between 1.32 and 1.78.
## Actor Thread
0: Prints all producer threads data in descending order based on price.
1: Prints data from Producer A.
2: Prints data from Producer B.
3: Prints data from Producer C.
4: Prints data from Producer D.
5: Does not print anything. 
