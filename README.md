# stock-market-demo
This is a demo project to access and update stock market data


Used the dataset from http://archive.ics.uci.edu/ml/datasets/Dow+Jones+Index#, converted the .data to .csv format and the csv data has been uploaded to h2 inmemory database on application startup.

Project contains apis for below operations

1. GetAllStocks 	- Fetch all available stocks from the database
2. GetStockById 	- Fetch all stocks with stock tikcer/ID (if 'AXP' is        			  passed all stocks will be retrieved)
3. UploadBulkData - upload bulk data using csv file
4. AddStocks      - Add new records to database

On application startup: 

H2-console will be available at the below URL

http://localhost:9090/h2-console

** Please verify the data source properties if you are unable to access the h2 console.

Swagger-UI: API Documentation can be accessed at the below URL

http://localhost:9090/swagger-ui.html



