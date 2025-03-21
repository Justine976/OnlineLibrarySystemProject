<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link href="icon/library-logo.png" rel="icon">
        <title>Online Library</title>
        <link rel="stylesheet" href="newcss.css" />
    </head>
    <body>
        
        <header>
            <label for="menu-toggle" class="menu-icon">&#9776;<span>Menu</span></label>
            <h1>Online Library</h1>
        </header>

        <!-- Hidden checkbox for toggling sidebar on mobile -->
        <input type="checkbox" id="menu-toggle">

        <!-- Navigation -->
        <nav>
            <a href="index.html" title="Home">
              <img src="icon/home.png" alt="Home" class="icon" />
              <span>Home</span>
            </a>
        </nav>

        <!-- Main Content -->
        <main>
            <aside>
                <h2>Welcome to the Online Library</h2>
                <p>We are thrilled to have you join our vibrant community of readers and lifelong learners. 
                    Our platform offers an extensive collection of books, research articles, and multimedia resources designed to fuel your curiosity and enhance your knowledge. 
                    Whether you're pursuing academic excellence, professional development, or simply enjoying a good story, our easy-to-navigate interface ensures that you can explore and access valuable content anytime, anywhere. 
                    Dive in, discover new ideas, and let the journey of learning inspire you every day!
                </p>
            </aside>
            <section>
                <form action="MyServlet" method="post">
                    <div class="loginout">
                        <h2>Log In/Log Out</h2>
                        <div class="inout">
                            <label for="id_number">ID Number: </label>
                            <input type="text" id="id_number" name="id_number" required pattern="\d{6}"/>
                            <input type="submit" value="Log In/Out" />
                        </div>
                        <div class="inout">
                            <h1 class="error">
                            <!-- Display error if available -->
                            <c:if test="${not empty errorMessage}">
                              <div class="error">${errorMessage}</div>
                            </c:if>
                            </h1>
                              <h1 class="name">${firstName} ${lastName}</h1>
                        </div>
                        <div class="inout">
                            <h2>${date}</h2>
                            <h2>${time}</h2>
                        </div>
                    </div>
                </form>
            </section>
        </main>
        
        <footer>
            <p>&copy; 2025 Online Library. All rights reserved.</p>
        </footer>
        <script>
            const inputField = document.getElementById('id_number');
            // Focus the input field when any key is pressed
            document.addEventListener('keydown', (event) => {
                // Check if the input field is not already focused
                if (document.activeElement !== inputField) {
                    inputField.focus();
                }
            });
        </script>
    </body>
</html>
