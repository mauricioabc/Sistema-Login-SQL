-- Cria Banco
create database Dados;
use Dados;

-- Cria Schemas
create schema auth;

/****** Object:  Table [auth].[User]    Script Date: 06/06/2023 15:41:38 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [auth].[User](
	[id] [uniqueidentifier] NOT NULL,
	[Email] [varchar](255) NULL,
	[InsertDate] [datetime] NULL,
	[Nome] [varchar](255) NULL,
	[PasswordHash] [varchar](max) NULL,
	[UserTypeId] [uniqueidentifier] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO

ALTER TABLE [auth].[User]  WITH CHECK ADD  CONSTRAINT [FKi4xjqcu9fm2cqsktrmal8tehs] FOREIGN KEY([UserTypeId])
REFERENCES [auth].[UserType] ([id])
GO

ALTER TABLE [auth].[User] CHECK CONSTRAINT [FKi4xjqcu9fm2cqsktrmal8tehs]
GO

/****** Object:  Table [auth].[UserType]    Script Date: 06/06/2023 15:41:59 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [auth].[UserType](
	[id] [uniqueidentifier] NOT NULL,
	[Descricao] [varchar](255) NULL,
	[InsertDate] [datetime] NULL,
	[Nome] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

/****** Object:  Table [auth].[Config]    Script Date: 06/06/2023 15:42:25 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [auth].[Config](
	[Id] [uniqueidentifier] NOT NULL,
	[PrivateKey] [varchar](max) NULL,
	[PublicKey] [varchar](max) NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
