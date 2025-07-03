# Data-driven-login-automation-test-for-Rokomari.com

🖥️✨ Automated Login Test for Rokomari.com — Data-Driven with Excel 📊 + Selenium + TestNG

I’ve recently developed a login automation framework for https://www.rokomari.com/login using Java, Selenium WebDriver, TestNG, ExtentReports, and Excel data-driven testing.

The framework covers:
✅ Valid email/password login test
✅ Invalid email/login scenarios
✅ Proper reporting with screenshots on success and failure
✅ Data-driven test execution using TestNG DataProvider from Excel

Features:
📌 Excel-integrated data source — manage test data easily
📌 Detailed ExtentReports with screenshots on both pass and fail cases
📌 Soft failures and clean session management before each login attempt
📌 Dynamic element validation after login attempt (success/failure cases)

Tech Stack:
🛠️ Java | Selenium WebDriver | TestNG | ExtentReports | Apache POI (for Excel)

Here’s a quick workflow:
1️⃣ Read email/password/expected result from Excel
2️⃣ Attempt login at https://www.rokomari.com/login
3️⃣ Check whether login was successful (by URL or dashboard element)
4️⃣ Capture screenshot and generate ExtentReport based on result

✅ Successfully handled issues like:

Password & login button not appearing on invalid mail

Retaining session state causing valid login failure (solved with cookie clearing and field resets)

💡 If you're working with Selenium automation, data-driven testing is a must-have for scalable, reliable, and clean test cases.
