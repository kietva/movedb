
# MOVEDB

This is a simple app build of the Android Clean Architecture

##How to build on your environment
Add your API key in local.properties file.
```
TOKEN = ACCESS TOKEN
```
You can create new key from [here](https://www.themoviedb.org/).

## About
It simply loads Popular Movies list from TMDB-API and stores it in persistence storage (i.e. Room Database). Movies list will be always loaded from local database. Remote data (from API) and Local data is always synchronized. It has feature of bookmark movie which will be stored locally.

What is clean architecture?
Architecture means the overall design of the project. It's the organization of the code into classes or files or components or modules. And it's how all these groups of code relate to each other. The architecture defines where the application performs its core functionality and how that functionality interacts with things like the database and the user interface.

Why the cleaner approach?
Separation of code in different layers with assigned responsibilities making it easier for further modification.
High level of abstraction
Loose coupling between the code
Testing of code is painless
Clean code always looks like it was written by someone who cares. - by Michael Feathers”

Layers
Domain - Would execute business logic which is independent of any layer and is just a pure kotlin/java package with no android specific dependency.
Data - Would dispense the required data for the application to the domain layer by implementing interface exposed by the domain.
Presentation / framework - Would include both domain and data layer and is android specific which executes the UI logic.
- Modules of App
### App
It uses the all the components and class releated to Android Framework. It gets the data from presentation layer and shows on UI.
#### Presentation
This layer's responsibility is to handle the presentation of the User Interface, but at the same time knows nothing about the user interface itself. This layer has no dependence on the Android Framework, it is a pure Kotlin module. Each ViewModel class that is created implements the ViewModel class found within the Architecture components library. This ViewModel can then be used by the UI layer to communicate with UseCases and retrieve data.

### Data
The Data layer is our access point to external data layers and is used to fetch data from multiple sources (the cache and remote in our case).

### Domain
The domain layer responsibility is to simply contain the UseCase instance used to retrieve data from the Data layer and pass it onto the Presentation layer.


## License

MIT License

Copyright (c) 2022 KIETVA

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

