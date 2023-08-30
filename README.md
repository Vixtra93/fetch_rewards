# Sample Fetch Test

A simple app that loads information from an API to demonstrate an approach to using some of the best practices in Android development. 
Including:</br>

* **LiveData**
* **ViewModel**
* **Hilt (for dependency injection)**
* **Kotlin Coroutines**
* **Retrofit**
  
## The following diagram shows the modules and their application interactions:
<p align="center">
  <img src="project/assets/mvvm_diagram.png" alt="Texto alternativo" />
  <br />
  Example: Architecture diagram.
</p>

* **Retrofit:** Library that allows us to easily obtain data from an API on the web.</br>
* **Repository:** This design pattern creates a good data access strategy.</br>
* **ViewModel:** It is in charge of accessing the data and performing the necessary transformations to display them in the view.
* **LiveData:** It is a variable where observable data is stored. Another component will observe them and act accordingly.
* **Hilt:** To perform Dependency Injection, or Dependency Injection. Based on the popular Dagger.</br></br>
## Screenshot
<p align="center">
<img src="project/assets/screen_fetch.jpg" alt="Texto alternativo" width="300" height="550" />
</p>


