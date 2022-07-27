package com.example.submission

object LanguageData {

    private val languagesRank = arrayOf(
        1,
        2,
        3,
        4,
        5,
        6,
        7,
        8,
        9,
        10
    )

    private val languagesName = arrayOf(
        "Python",
        "JavaScript",
        "Java",
        "C#",
        "C",
        "C++",
        "Go",
        "R",
        "Swift",
        "PHP"
    )

    private val languagesNumberOfJobs = arrayOf(
        "19,000",
        "24,000",
        "29,000",
        "18,000",
        "8,000",
        "9,000",
        "1,700",
        "1,500",
        "1,800",
        "7,000",
    )

    private val languages_avg_annual_salary = arrayOf(
        "\$120,000",
        "\$118,000",
        "\$104,000",
        "\$97,000",
        "\$97,000",
        "\$97,000",
        "\$93,000",
        "\$93,000",
        "\$93,000",
        "\$81,000",
    )

    private val languages_desc = arrayOf(
        "Python is widely regarded as a programming language that’s easy to learn, due to its simple syntax, a large library of standards and toolkits, and integration with other popular programming languages such as C and C++. In fact, it’s the first language that students learn in the Align program, Gorton says. “You can cover a lot of computer science concepts quickly, and it’s relatively easy to build on.” It is a popular programming language, especially among startups, and therefore Python skills are in high demand.",
        "JavaScript is the most popular programming language for building interactive websites; “virtually everyone is using it,” Gorton says. When combined with Node.js, programmers can use JavaScript to produce web content on the server before a page is sent to the browser, which can be used to build games and communication applications that run directly in the browser. A wide variety of add-ons extend the functionality of JavaScript as well.",
        "Java is the programming language most commonly associated with the development of client-server applications, which are used by large businesses around the world. Java is designed to be a loosely coupled programming language, meaning that an application written in Java can run on any platform that supports Java. As a result, Java is described as the “write once, run anywhere” programming language.",
        "Microsoft developed C# as a faster and more secure variant of C. It is fully integrated with Microsoft’s .NET software framework, which supports the development of applications for Windows, browser plug-ins, and mobile devices. C# offers shared codebases, a large code library, and a variety of data types.",
        "Along with Python and Java, C forms a “good foundation” for learning how to program, Gorton says. As one of the first programming languages ever developed, C has served as the foundation for writing more modern languages such as Python, Ruby, and PHP. It is also an easy language to debug, test, and maintain.",
        "C++ is an extension of C that works well for programming the systems that run applications, as opposed to the applications themselves. C++ also works well for multi-device and multi-platform systems. Over time, programmers have written a large set of libraries and compilers for C++. Being able to use these utilities effectively is just as important to understanding a programming language as writing code, Gorton says.",
        "Also referred to as Golang, Go was developed by Google to be an efficient, readable, and secure language for system-level programming. It works well for distributed systems, in which systems are located on different networks and need to communicate by sending messages to each other. While it is a relatively new language, Go has a large standards library and extensive documentation.",
        "R is heavily used in statistical analytics and machine learning applications. The language is extensible and runs on many operating systems. Many large companies have adopted R in order to analyze their massive data sets, so programmers who know R are in great demand. ",
        "Swift is Apple’s language for developing applications for Mac computers and Apple’s mobile devices, including the iPhone, iPad, and Apple Watch. Like many modern programming languages, Swift has a highly readable syntax, runs code quickly, and can be used for both client-side and server-side development. ",
        "PHP is widely used for server-side web development, when a website frequently requests information from a server. As an older language, PHP benefits from a large ecosystem of users who have produced frameworks, libraries, and automation tools to make the programming language easier to use. PHP code is also easy to debug.",
    )

    private val languages_img = arrayOf(
        R.drawable.python,
        R.drawable.js,
        R.drawable.java,
        R.drawable.c_sharp,
        R.drawable.c,
        R.drawable.cpp,
        R.drawable.go,
        R.drawable.r,
        R.drawable.swift,
        R.drawable.php
    )

    val listData: ArrayList<Language>
        get(){
            val list = arrayListOf<Language>()
            for (position in languagesName.indices){
                val lang = Language()
                lang.rank = languagesRank[position]
                lang.name = languagesName[position]
                lang.numOfJobs = languagesNumberOfJobs[position]
                lang.avg_annual_salary = languages_avg_annual_salary[position]
                lang.language_img = languages_img[position]
                lang.language_desc = languages_desc[position]
                list.add(lang)
            }
            return list
        }
}