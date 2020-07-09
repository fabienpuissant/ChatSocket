import React, { Component, createRef } from "react";
import SockJsClient from "react-stomp";

const axios = require("axios").default;

export class Comment extends Component {
  constructor(props) {
    super(props);
    this.state = {
      comment: "",
      initMessages: [],
      messages: [],
    };

    this.commentInput = createRef();
    this.handleSubmitCLick = this.handleSubmitCLick.bind(this);
    this.sendMessage = this.sendMessage.bind(this);
    this.refreshMessages = this.initMessages.bind(this);
    this.updateMessages = this.updateMessages.bind(this);
  }

  componentDidMount() {
    this.initMessages();
  }

  handleSubmitCLick(e) {
    e.preventDefault();
    var comment = this.commentInput.current.value;
    this.commentInput.current.value = "";
  }

  sendMessage(e) {
    e.preventDefault();
    var comment = this.commentInput.current.value;
    this.commentInput.current.value = "";
    this.clientRef.sendMessage("/topics/DPMO", comment);
    axios.post("http://localhost:8080/CommentService/addComment", {
      description: comment,
      sender: "JOSE",
      room: "DPMO",
    });
  }

  initMessages() {
    var self = this;
    axios
      .get("http://localhost:8080/CommentService/getByRoom/DPMO")
      .then(function (response) {
        console.log(response.data);
        self.setState({
          initMessages: response.data,
        });
      })
      .catch(function (error) {
        // handle error
        console.log(error);
      });
  }

  updateMessages(msg) {
    var newMessages = this.state.messages.slice();
    console.log(this.state.messages);
    newMessages.push(msg);
    this.setState((prevState) => ({
      messages: [...prevState.messages, msg],
    }));
  }

  render() {
    const { initMessages, messages } = this.state;
    return (
      <div className="container mt-4">
        <div className="chat-container container mt-4">
          <ul className="list-group mt-4">
            {initMessages.map((message, index) => (
              <li className="list-group-item" key={index}>
                {message.description}
              </li>
            ))}

            {messages.map((message, index) => (
              <li className="list-group-item" key={index}>
                {message}
              </li>
            ))}
          </ul>
        </div>

        <SockJsClient
          url="http://localhost:8080/chat"
          topics={["/topics/DPMO"]}
          onMessage={(msg) => this.updateMessages(msg)}
          ref={(client) => {
            this.clientRef = client;
          }}
        />
        <form>
          <div className="form-group mt-4">
            <label htmlFor="comment">Leave a comment</label>
            <input
              type="text"
              className="form-control"
              ref={this.commentInput}
            ></input>
            <button className="btn btn-primary mt-4" onClick={this.sendMessage}>
              Submit
            </button>
          </div>
        </form>
      </div>
    );
  }
}

export default Comment;
