# BSC

Application keeps records of packages processed on postcodes. There is also possiblity to add fees for different weight limits. Result is printed in 60 second intervals.

- {weight} {post-code} - Default command. Adds weight to postcode. If there is already some weight, new weight is added to old one.
                        Format of line is:
                        <weight: positive number, >0, maximal 3 decimal places, . (dot) as decimal separator>
                        <space><fee: positive number, >=0, fixed two decimals, . (dot) as decimal separator>
													
- add-file {path-to-file} - Loads file which contains weights and post codes. Data inside this file are used in the same way as in default command and also should be in the same format. Format of this command is: add-file <path-to-file>
	
- fee-file {path-to-file} - Loads file which contains weights and fees. Data inside this file are used for adding fees to each post code.
                          Format for fee row is:
                          <weight: positive number, >0, maximal 3 decimal places, . (dot) as decimal separator><space>
                          <fee: positive number, >=0, fixed two decimals, . (dot) as decimal separator>
                          Format of this command is:
                          add-file <path-to-file>
														
- print - Prints result in format:
        <postal code: fixed 5 digits><space><total weight: fixed 3 decimal places, . (dot) as decimal separator><space>
        <total fee: fixed 2 decimal places, . (dot) as decimal separator)
							 
- quit - Stops application.

- help - Prints all command descriptions
