let loadBooks = document.getElementById('loadBooks')

loadBooks.addEventListener('click', onLoadBooks);

function onLoadBooks(event){
    var requestOptions = {
        method: 'GET',
        redirect: 'follow'
    };

    let authorsContainer = document.getElementById('authors-container')
    authorsContainer.innerHTML = ''

    fetch("http://localhost:8080/books/all", requestOptions)
        .then(response => response.json())
        .then(json => json.forEach(book => {
            //create elements and add them to the table
            let row = document.createElement('tr')

            let titleCol = document.createElement('td')
            let authorCol = document.createElement('td')
            let isbnCol = document.createElement('td')
            let actionCol = document.createElement('td')

            titleCol.textContent = book.title
            authorCol.textContent = book.author.name
            isbnCol.textContent = book.isbn


            //add columns to the parent col
            row.appendChild(titleCol)
            row.appendChild(authorCol)
            row.appendChild(isbnCol)
            row.appendChild(actionCol)


            authorsContainer.appendChild(row);
        }))
        .catch(error => console.log('error', error));

}