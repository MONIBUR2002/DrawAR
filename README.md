# AR Drawing App 🎨📱

Welcome to **AR Drawing App** – an innovative Android application that lets you create stunning AR drawings using your device's camera and cutting-edge technology!

![AR Drawing App Screenshot](./assets/screenshot.png)

## Overview 🌟

**AR Drawing App** is built in Android Studio using Kotlin and Jetpack Compose. It leverages advanced technologies such as MVVM architecture, Hilt for dependency injection, Coil for image loading, Firebase for the online image library, and the OpenCV SDK for real-time image edge detection using the Canny algorithm. This app is designed for artists, developers, and tech enthusiasts looking to blend creativity with technology.

### What It Does ✍️

**AR Drawing App** is tailored for hand drawing on paper using an Android phone. The app uses the device's camera to capture your pencil and paper, then overlays the image on the screen. This allows you to see both your drawing surface and a live reference image simultaneously. You can adjust the opacity of the overlay, enabling you to trace or draw directly on your paper with precision and clarity. This augmented reality experience enhances the traditional hand drawing process by providing real-time visual guidance.

## Features 🚀

- **Augmented Reality Drawing:** Draw directly in your environment with AR.
- **Real-Time Edge Detection:** Apply the Canny algorithm to detect image edges instantly using OpenCV.
- **Online Image Library:** Seamlessly integrate with Firebase to store and retrieve your creations.
- **Modern Architecture:** Built with MVVM for a clean, maintainable codebase.
- **Dependency Injection:** Managed via Hilt for streamlined development.
- **Efficient Image Loading:** Powered by Coil for smooth image handling.
- **Modern UI:** Crafted with Jetpack Compose for a sleek, responsive design.
- **Adjustable Overlay:** Modify the opacity of the camera overlay for enhanced drawing precision.

## Technology Stack 🛠️

| **Component**             | **Technology**        | **Description**                                         |
|---------------------------|-----------------------|---------------------------------------------------------|
| **Language**              | Kotlin                | Primary language for Android development              |
| **UI Toolkit**            | Jetpack Compose       | Declarative UI framework for building native apps       |
| **Architecture**          | MVVM                  | Structured design pattern for separation of concerns    |
| **Dependency Injection**  | Hilt                  | Simplifies dependency management in Android projects    |
| **Image Loading**         | Coil                  | Fast and lightweight image loading library              |
| **Online Storage**        | Firebase              | Cloud-based storage and database for images             |
| **Image Processing**      | OpenCV SDK            | Provides edge detection (Canny algorithm) capabilities    |

## Installation & Setup 📦

### Prerequisites
- **Android Studio:** Latest version recommended
- **Kotlin:** Ensure Kotlin is installed and updated

### Clone the Repository
```bash
git clone https://github.com/yourusername/DrawAR.git
cd ARDrawing
