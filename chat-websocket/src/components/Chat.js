import React, { useState, useEffect, createRef } from 'react'


export default function Chat() {

    const axios = require('axios').default;

    const [messages, setmessages] = useState([])

    useEffect(() => {
        axios.get("http://localhost:8080/CommentService/getAll")
        .then(function(response){
            setmessages(response.data)
            console.log(response.data)
        })
        .catch(function (error) {
            // handle error
            console.log(error); 
          })
      }, []);

    return (
        <div className="chat-container container mt-4">
             
            <ul className="list-group mt-4">
                { messages.map((message, index) => 
                    <li className="list-group-item" key={index}>{message.description}</li>
                )
                }
            </ul>
        </div>
    )
}

