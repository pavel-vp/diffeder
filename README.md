# diffeder
Coding task for Waes

## Task description

• Provide 2 http endpoints that accepts JSON base64 encoded binary data on both endpoints

	o <host>/v1/diff/<ID>/left and <host>/v1/diff/<ID>/right
• The provided data needs to be diff-ed and the results shall be available on a third end point
	o <host>/v1/diff/<ID>
• The results shall provide the following info in JSON format
	o If equal return that
	o If not of equal size just return that
	o If of same size provide insight in where the diffs are, actual diffs are not needed.
		§ So mainly offsets + length in the data
• Make assumptions in the implementation explicit, choices are good but need to be communicated

## Technical stack:
- Java 8
- SpringBoot, SpringJPA
- H2
- JUnit
- Mockito
- Lombok
- Maven

## Compile and run tests
mvn clean package

## Run the program
java -jar target/diffeder-0.0.1-SNAPSHOT.jar 
