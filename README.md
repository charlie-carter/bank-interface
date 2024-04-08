# My Personal Project

## Phase 0

**What will my project do:**

My project will be a banking interface that allows users to store money in multiple accounts, 
transfer money between account, "Deposit" and "withdraw" money (for demonstration purposes only),
and allow users to buy banking products and assets from the bank. 

**Who will use it:**

My application will be used by clients of a bank.

**Why is this of interest to me:**

I'm interested in all the security measures and regulations around digital banking,
and this seems like a good way to explore all the details of banking systems that the client
usually never gets to see. 

## User Stories:

- I want to be able to create a person at a bank, and add accounts under that person
- I want to be able to view how much each account is worth at any given time
- I want to be able to transfer money between accounts 
- I want to be able to deposit and withdraw money from any account

### Phase 2 user stories:

- As a client of the bank, I want to be able to save the status of my finances when I leave the application (If I choose)
- As a client of the bank, I want to be able to load a saved version of my accounts and assets at the bank (If I choose)

### Phase 3 user stories:

- As a client of the bank, I want to be able to see a list of all my current accounts and assets
- As a client of the bank, I want to be able to view details of any accounts or asset, and withdraw/deposit money from any account through the GUI
- As a client of the bank, I want to be able to change my 
- password through a GUI


### Phase 4 part 2:
Sun Apr 07 22:20:41 PDT 2024
Added Account #826708 - Savings - $5000.00

Sun Apr 07 22:20:57 PDT 2024
Added Bond - Asset No: 8574452

Sun Apr 07 22:21:12 PDT 2024
$400.0 withdrawn from account #826708

Sun Apr 07 22:21:20 PDT 2024
Data Saved to File

### Phase 4 part 3:
There are two things I would change about the current structure of my program. First, I would 
change the way GUI classes access the FontBook class. The FontBook class is a way to keep fonts 
consistent across all pages, and instead of most GUI classes having to make their own instance
of the FontBook class, I would change it to allow all classes access one instance of it globally.
This could be done using the singleton design pattern. Secondly, I want to change the way I 
structured assets in my project. Because the three types of assets (Bonds, Stocks, and GICs) all
had slightly different information that needed to be stored, they all only shared a few common 
fields, and most had their own fields that were specific to the type of asset. This became quite
problematic when Asset information had to be printed or shown to the user on screen, as it usually
required three individual methods to structure the information based on the type of asset. It 
was also problematic when it came time to create new assets through UI/GUI, as it again required
three different methods or classes to create a new asset. In the future, I would change this to 
abstract all the Asset fields into the Asset abstract class, and try to have the different assets
share the fields as much as possible, although this will ultimately lead to some instances leaving
some fields NA or blank, as some types of assets just have more information that comes along with 
them. 



## For grader:
### Username is "John Lastname", password is "password123"