### Lab, In-Class Exercise For Week 2 ###

#### Encryption and Password Management -- The Contract and Unit Testing ####

##### Together in the lab #####

1. Go over basic concept of encryption, stressing the contract
  1. Encryption and decryption are both string to string mappings 
  1.  If s1 == s2 then encrypt(s1) must == encrypt(s2)
  1.  If s1 != s2 then encrypt(s1) must probably != encrypt(s2)
  1.  It must be difficult to infer s1 from encrypt(s1)
  1.  If decrypt(s) is defined, then decrypt(encrypt(s)) == s


Today we will deal with a simple form of encryption called substitution cypher, which defines a mapping from characters to characters.   You could do this by defining the mapping explicitly (i.e. a dictionary) or by a method like what the Caesar Cypher does:  just define an indexed set of characters and an offset.

Encryptor interface -- string to string encrypt.  Possible decrypt, introduce OperationNotImplementedException
Caesar cypher -- takes an offset.  Discuss how we choose and communicate the set of characters -- in this case we will choose to pre-enumerate the character set as an array of characters.   This is mainly so we can use it for 
password applications that have restrictions on character sets.

In class, develop the class using a fixed set of characters. Also consider two 
implementations, one that maintains two arrays, one which does the offset each time.
Discuss the pros and cons of each alternative.

Emphasize starting by making the contract clear, writing unit tests, and test-based development.

##### Individually in the lab #####

Extend the implementation of Caesar Cypher to do decryption -- see how much code you can share with encrypt.
Extend unit tests.

Extra credit:  implement ROT13
# assignment_2
