#  Wallet Analyzer

Wallet Analyzer is a full-stack application that allows users to explore the historical activity of any Ethereum wallet address. It visualizes token transactions, fee history, airdrop activity, and frequently used tokens in a rich and interactive dashboard.

##  Features

-  View all token transactions of a wallet
-  Identify most-used tokens and their usage count
-  Analyze gas fees over time
-  Detect airdrops received by the wallet
-  Visual and exportable dashboard (PDF/PNG)
-  Dark mode & responsive design

##  Tech Stack

- **Backend:** Java 17, Spring Boot, WebClient, Etherscan API
- **Frontend:** React + Vite, TailwindCSS, Recharts
- **Other:** Axios, jsPDF, html2canvas, React Router
###  How to Get an Etherscan API Key

1. Go to [https://etherscan.io/myapikey](https://etherscan.io/myapikey)
2. Sign in or create an account.
3. Click **"Add"** to generate a new API key.
4. Copy the API key and add it to your backend configuration.

Example (for `application.properties`):

###  Example Wallet Address

To test the application, you can use any Ethereum wallet address. Here is a sample:
```bash
0xde0B295669a9FD93d5F28D9Ec85E40f4cb697BAe

```
You can look example ethereum address from this web site 
```bash
https://etherscan.io/accounts/8
```
###  Swagger UI
```bash
 http://localhost:8313/swagger-ui/index.html#/
```
##  Project Structure
/backend: Spring Boot API server

/frontend: React Vite frontend app
##  Screenshot

![wallet](https://github.com/user-attachments/assets/e3486b90-21a5-442e-9324-9bd57a4b0806)
![1_ether](https://github.com/user-attachments/assets/89f7e56c-d6a0-4fc7-880b-47e48ee15dcb)
![2_ether](https://github.com/user-attachments/assets/89c9f5b6-f704-40bc-b645-791465e32fa2)
![3_ether](https://github.com/user-attachments/assets/77c362a9-c4c4-4c28-a900-61229693220c)
![4_ether](https://github.com/user-attachments/assets/ccf3f56a-f8b7-42e8-a2f4-58448ad3ccd1)


##  Environment Variables

Create a `.env` file or set the following variables:

```properties
ETHERSCAN_API_KEY=your_api_key_here
```

##  Run the Project
```bash
# Backend
cd backend
mvn spring-boot:run

# Frontend
cd frontend
npm install
npm run dev
```

## 📄 License
